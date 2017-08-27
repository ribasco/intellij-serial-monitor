package com.intellij.plugins.serialmonitor.ui;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.TextFieldWithHistory;

import java.util.ArrayList;

/**
 * @author Dmitry_Cherkas
 */
public final class CommandsComboBox extends TextFieldWithHistory {

    private final static String HISTORY_KEY = "serialMonitor.commands";
    private Project myProject;

    public void setProject(Project myProject) {
        this.myProject = myProject;
        reset();
    }

    public void addCurrentTextToHistory() {
        super.addCurrentTextToHistory();
        PropertiesComponent.getInstance(myProject).setValue(HISTORY_KEY, StringUtil.join(getHistory(), "\n"));
    }

    public void reset() {
        final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance(myProject);
        final String history = propertiesComponent.getValue(HISTORY_KEY);
        if (history != null) {
            final String[] items = history.split("\n");
            ArrayList<String> result = new ArrayList<String>();
            for (String item : items) {
                if (item != null && item.length() > 0) {
                    result.add(item);
                }
            }
            setHistory(result);
            setSelectedItem("");
        }
    }
}
