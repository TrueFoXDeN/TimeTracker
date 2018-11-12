package data;

import gui.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TaskGrabber {

    public static boolean[] programsRunning() {
        boolean[] programs = new boolean[Gui.apps.size()];

        String listOfProcesses = getCommandOutput("tasklist");

        for (int i = 0; i < Gui.apps.size(); i++) {
            if (listOfProcesses == null || listOfProcesses.isEmpty()) {
                System.err.println("Unable to automatically determine if " + Gui.apps.get(i).getExename() + " is running");
            } else {
                programs[i] = listOfProcesses.contains(Gui.apps.get(i).getExename());
            }
        }

        return programs;
    }

    private static String getCommandOutput(String command) {
        String output = null;

        Process process;
        BufferedReader reader = null;
        InputStreamReader streamReader = null;
        InputStream stream = null;

        try {
            process = Runtime.getRuntime().exec(command);

            stream = process.getInputStream();
            streamReader = new InputStreamReader(stream);
            reader = new BufferedReader(streamReader);

            String currentLine;  //store current line of output from the cmd
            StringBuilder commandOutput = new StringBuilder();  //build up the output from cmd
            while ((currentLine = reader.readLine()) != null) {
                commandOutput.append(currentLine + "\n");
            }

            int returnCode = process.waitFor();
            if (returnCode == 0) {
                output = commandOutput.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                System.err.println("Cannot close stream input! " + e);
            }
        }
        if (streamReader != null) {
            try {
                streamReader.close();
            } catch (IOException e) {
                System.err.println("Cannot close stream input reader! " + e);
            }
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Cannot close reader! " + e);
            }
        }

        return output;

    }
}