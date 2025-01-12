import io.muzoo.ssc.assignment.tracker.SscAssignment;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.util.HashSet;

public class Main extends SscAssignment {

    public static void main(String[] args) {
        //For the options in the command lines such as "-f", "-p", etc.
        Options options = new Options();
        options.addOption("p", false, "Print all the paths of duplicates");
        options.addOption("c", false, "count duplicate(s) in the directory/ subdirectory");
        options.addOption("a", true, "The specified algorithm for the finding (Must be Either: 'bbb', 'sha256', or 'mad25' ");
        options.addOption("f", true, "Starting Path (Directory)");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

        HashSet algoCheck = new HashSet();
        algoCheck.add("bbb");
        algoCheck.add("sha256");
        algoCheck.add("md5");

        String path = cmd.getOptionValue("f");
        String algo = cmd.getOptionValue("a");
        boolean countDu = cmd.hasOption("c");
        boolean printPaths = cmd.hasOption("p");

        //there's a chance the value comes in different from what we want
        if(!algoCheck.contains(algo)) {
            System.err.println("Invalid algorithm. Use 'bbb', 'sha256', or 'md5' ");
            return;
        }

        Path startingPath = Paths.get(path);

        if(!Files.isDirectory(startingPath)) {
            System.err.println("Invalid Directory. This Path doesn't exist");
            return;
        }

    }



}

