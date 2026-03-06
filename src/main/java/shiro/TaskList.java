package shiro;

import java.util.ArrayList;

/**
 * Represents a list of tasks and operations that can be performed on them.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list using an existing list of tasks.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove.
     * @return Removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns tasks whose descriptions contain the specified keyword.
     *
     * @param keyword Keyword used to search tasks.
     * @return TaskList containing matching tasks.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return new TaskList(matchingTasks);
    }

    /**
     * Returns all tasks stored in the task list.
     *
     * @return List of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}