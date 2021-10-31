package frc.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.DoubleSupplier;

import frc.robot.abstraction.SwartdogCommand;
import frc.robot.abstraction.SwartdogSubsystem;

public class RobotLog extends SwartdogSubsystem 
{
    public enum CommandMode
    {
        Start,
        Exec,
        End
    }

    private String _filePath;
    private PrintWriter _fileWriter;

    private HashSet<String> _keys;

    private DoubleSupplier _battery;

    private boolean        _previouslyOpened;

    private int            _time;
    private int            _gameTime;

    public RobotLog(String file, DoubleSupplier battery) 
    {
        _filePath   = file;
        _battery    = battery;

        _fileWriter = null;

        _previouslyOpened = false;
        
        _keys       = new HashSet<String>();

        addFields("time", "gameTime", "vbat", "commandMode", "command", "gameMode");
    }

    @Override
    public void periodic() 
    {
        _time++;
        _gameTime++;
    }

    public void addFields(String... keys)
    {
        if (!_previouslyOpened)
        {
            _keys.addAll(new HashSet<String>(Arrays.asList(keys)));
        }
    }

    public void logCommand(SwartdogCommand command, CommandMode mode, Map<String, String> logParams)
    {
        Map<String, String> lineMap = new HashMap<>();

        lineMap.put("time",     Integer.toString(_time));
        lineMap.put("gameTime", Integer.toString(_gameTime));

        lineMap.put("vbat",     Double.toString(_battery.getAsDouble()));

        switch(mode)
        {
            case Start:
                lineMap.put("commandMode", "start");

                break;

            case Exec:
                lineMap.put("commandMode", "exec");

                break;

            case End:
                lineMap.put("commandMode", "end");

                break;

            default:
                break;
        }

        lineMap.put("command", command.getName());

        lineMap.putAll(logParams);

        write(lineMap);
    }

    public void logGameMode(GameMode newMode)
    {
        Map<String, String> lineMap = new HashMap<>();

        lineMap.put("time",     Integer.toString(_time));
        lineMap.put("gameTime", Integer.toString(_gameTime));

        _gameTime = 0;

        lineMap.put("vbat",     Double.toString(_battery.getAsDouble()));

        lineMap.put("gameMode", newMode.toString().toLowerCase());

        write(lineMap);

        if (newMode == GameMode.Disabled)
        {
            _fileWriter = close(_fileWriter);
        }
    }

    public void write(Map<String, String> params)
    {
        if (_fileWriter == null)
        {
            _fileWriter = open(_filePath);
        }

        Map<String, String> lineMap;
        lineMap = new HashMap<String, String>();


        for (String key : _keys)
        {
            lineMap.put(key, "");
        }

        ArrayList<String> keys = new ArrayList<String>(_keys);
        for (Map.Entry<String, String> kvp : params.entrySet())
            {
                if (keys.indexOf(kvp.getKey()) >= 0)
                {
                    lineMap.put(kvp.getKey(), kvp.getValue());
                }
            }

        System.out.println(lineMap.values().toString());
        _fileWriter.println(String.join(",", lineMap.values()));
    }

    public PrintWriter open(String filePath) {
        PrintWriter fileWriter = null;
        
        if (filePath != null)
        {
            try 
            {
                if (!_previouslyOpened)
                {
                    File file = new File(_filePath);
                    file.delete();

                    fileWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "utf-8")));
                    fileWriter.println(String.join(",", _keys));

                    _previouslyOpened = true;
                }
                else 
                {
                    fileWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), "utf-8")));
                }
            }
            catch (Exception e)
            {
                fileWriter = null;
            }

            System.out.println(_keys.toString());

        }

        return fileWriter;
    }

    public PrintWriter close(PrintWriter fileWriter)
    {
        if (fileWriter != null)
        {
            try {
                fileWriter.close();
            }
            catch (Exception e) 
            {
                // avoid doing anything
            }
        }

        return null;
    }

    @Override
    public void setGameMode(GameMode mode) {
        return;
    }
}
