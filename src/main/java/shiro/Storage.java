package shiro;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Loads tasks from disk and saves tasks to disk using a simple text-based format.
 */
public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return Task list loaded from disk. Returns an empty list if the file does not exist.
     * @throws ShiroException If an IO error occurs while reading the file.
     */
    public ArrayList<Task> load() throws ShiroException {
        try {
            ensureParentFolderExists();
            if (!Files.exists(filePath)) {
                return new ArrayList<>();
            }

            List<String> lines = Files.readAllLines(filePath);
            ArrayList<Task> tasks = new ArrayList<>();

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    tasks.add(parseLineToTask(line));
                } catch (ShiroException e) {
                    System.out.println("     [Warning] Skipping corrupted line: " + line);
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new ShiroException("☹ OOPS!!! I couldn't load your saved tasks.");
        }
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param tasks Task list to save.
     * @throws ShiroException If an IO error occurs while writing the file.
     */
    public void save(ArrayList<Task> tasks) throws ShiroException {
        try {
            ensureParentFolderExists();

            ArrayList<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(taskToLine(task));
            }
            Files.write(filePath, lines);
        } catch (IOException e) {
            throw new ShiroException("☹ OOPS!!! I couldn't save your tasks.");
        }
    }

    private void ensureParentFolderExists() throws IOException {
        Path parent = filePath.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    private static Task parseLineToTask(String line) throws ShiroException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new ShiroException("Corrupted Data");
        }

        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        String description = parts[2].trim();

        boolean isDone;
        if (doneFlag.equals("1")) {
            isDone = true;
        } else if (doneFlag.equals("0")) {
            isDone = false;
        } else {
            throw new ShiroException("Corrupted Data");
        }

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new ShiroException("Corrupted Data");
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new ShiroException("Corrupted Data");
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new ShiroException("Corrupted Data");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    private static String taskToLine(Task task) {
        String doneFlag = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + doneFlag + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + doneFlag + " | " + task.getDescription() + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + doneFlag + " | " + task.getDescription() + " | " + event.from + " | " + event.to;
        } else {
            return "T | " + doneFlag + " | " + task.getDescription();
        }
    }
}
