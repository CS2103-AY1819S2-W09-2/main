package seedu.address.commons.core;

import java.util.Arrays;
import java.util.List;

/**
 * Container for all commonly used commands
 */
public class Commands {

    public static final String ADD = "add";
    public static final String ADD_REQUEST = "add request n/John Doe p/98765432";
    public static final String ADD_REQUEST_MODE = "add 1 n/John Doe p/98765432";
    public static final String ADD_HEALTH_WORKER = "add healthworker n/Daenerys Targaryen p/98765432";
    public static final String ADD_HEALTH_WORKER_MODE = "add 2 n/Daenerys Targaryen p/98765432";
    public static final String EDIT = "edit";
    public static final String EDIT_REQUEST = "edit request n/John Doe p/98765432";
    public static final String EDIT_REQUEST_MODE = "edit 1 n/John Doe";
    public static final String EDIT_HEALTH_WORKER = "edit healthworker n/Daenerys Targaryen p/98765432";
    public static final String EDIT_HEALTH_WORKER_MODE = "edit 2 n/Daenerys Targaryen p/98765432";
    public static final String LIST = "list";
    public static final String LIST_ALL_REQUEST = "list request";
    public static final String LIST_ALL_REQUEST_MODE = "list 2";
    public static final String LIST_ALL_HEALTHWORKER = "list healthworker";
    public static final String LIST_ALL_HEALTHWORKER_MODE = "list 1";
    public static final String ASSIGN = "assign";
    public static final String ASSIGN_REQUEST = "assign hw/1 r/2";
    public static final String COMPLETE = "complete";
    public static final String FILTER = "filter";
    public static final String FIND_REQUEST = "frequest";
    public static final String SELECT = "select";
    public static final String DELETE = "delete";
    public static final String CLEAR = "clr";
    public static final String EXPLAIN = "explain";
    public static final String HISTORY = "history";
    public static final String HELP = "help";
    public static final String EXIT = "exit";

    public static List<String> getAllCommands() {
        return Arrays.asList(ADD, ADD_REQUEST, ADD_REQUEST_MODE, ADD_HEALTH_WORKER, ADD_HEALTH_WORKER_MODE, EDIT,
                EDIT_REQUEST, EDIT_REQUEST_MODE, EDIT_HEALTH_WORKER, EDIT_HEALTH_WORKER_MODE, LIST, LIST_ALL_REQUEST,
                LIST_ALL_REQUEST_MODE, LIST_ALL_HEALTHWORKER, LIST_ALL_HEALTHWORKER_MODE, ASSIGN, ASSIGN_REQUEST,
                COMPLETE, FILTER, FIND_REQUEST, SELECT, DELETE, CLEAR, EXPLAIN, HISTORY, HELP, EXIT);
    }
}
