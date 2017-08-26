package com.intellij.plugins.serialmonitor;

import com.intellij.openapi.diagnostic.ErrorReportSubmitter;

/**
 * @author Dmitry_Cherkas
 */
public class SerialMonitorErrorReportSubmitter extends ErrorReportSubmitter {
    @Override
    public String getReportActionText() {
        return null;
    }
}
