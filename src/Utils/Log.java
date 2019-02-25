package Utils;

public class Log {
    public static void error(String tag,String logString)
    {
        // red
        System.out.println((char)27 + "[31m" + tag + " : " + logString);
    }

    public static void warning(String tag,String logString)
    {
        // yellow
        System.out.println((char)27 + "[33m" + tag + " : " + logString);
    }

    public static void info(String tag,String logString)
    {
        // default
        System.out.println((char)27 + "[39m" + tag + " : " + logString);
    }

    public static void okresponce(String tag,String logString)
    {
        // green
        System.out.println((char)27 + "[32m" + tag + " : " + logString);
    }

    public static void trace(String tag,String logString)
    {
        // blue
        System.out.println((char)27 + "[34m" + tag + " : " + logString);
    }
}