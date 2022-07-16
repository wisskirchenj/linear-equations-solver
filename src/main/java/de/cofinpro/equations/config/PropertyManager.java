package de.cofinpro.equations.config;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * PropertyManager class handling all application properties using java.util.Properties. On class initializing,
 * first defaults are set and then the min program calls processProperties with the argument list to set given
 * arguments. The class provides static methods for retrieving a property by key, resp. all properties.
 */
@Slf4j
public class PropertyManager {
    private static final Properties APP_CONFIG = new Properties();
    public static final String INPUT_FILE_OPTION = "-in";
    public static final String OUTPUT_FILE_OPTION = "-out";
    private static final Set<String> OPTIONS = new HashSet<>(Set.of(INPUT_FILE_OPTION, OUTPUT_FILE_OPTION));
    private static final String USAGE = "invalid arguments! use: -in <path> -out <path>";
    public static final String NO_SOLUTIONS_LABEL = "-no_solutions_label";
    public static final String INFINITE_SOLUTIONS_LABEL = "-infinite_solutions_label";


    private PropertyManager() {
        // no instances
    }

    static {
        APP_CONFIG.setProperty(NO_SOLUTIONS_LABEL, "No solutions");
        APP_CONFIG.setProperty(INFINITE_SOLUTIONS_LABEL, "Infinitely many solutions");
    }

    public static void processProperties(String[] args) {
        if (args.length != 2 * OPTIONS.size()) {
            errorExit();
        }
        for (int i = 0; i < args.length; i += 2) {
            if (!OPTIONS.contains(args[i])) {
                errorExit();
            }
            APP_CONFIG.setProperty(args[i], args[i + 1]);
            OPTIONS.remove(args[i]);
        }
        if (!OPTIONS.isEmpty()) {
            errorExit();
        }
    }

    private static void errorExit() {
        log.error(USAGE);
        System.exit(1);
    }

    public static Properties getProperties() {
        return APP_CONFIG;
    }

    public static String getProperty(String key) {
        return APP_CONFIG.getProperty(key);
    }
}
