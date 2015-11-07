package com.intellij.plugins.serialmonitor.service;

import com.intellij.plugins.serialmonitor.SerialMonitorException;
import jssc.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitry_Cherkas
 */
public class JsscSerialService implements SerialService {

    private SerialPort port;

    @Override
    public List<String> getPortNames() {
        return Arrays.asList(SerialPortList.getPortNames());
    }

    @Override
    public void connect(String portName, int baudRate) {
        int dataBits = SerialPort.DATABITS_8;
        int stopBits = SerialPort.STOPBITS_1;
        int parity = SerialPort.PARITY_NONE;

        try {
            port = new SerialPort(portName);
            port.openPort();
            boolean res = port.setParams(baudRate, dataBits, stopBits, parity, true, true);
            if (!res) {
                throw new SerialMonitorException("Failed to set SerialPort parameters");
            }
            port.addEventListener(new MySerialPortEventListener());
        } catch (SerialPortException e) {
            if (e.getPortName().startsWith("/dev") && SerialPortException.TYPE_PERMISSION_DENIED.equals(e.getExceptionType())) {
                throw new SerialMonitorException(String.format("Error opening serial port ''%s''. Try consulting the documentation at http://playground.arduino.cc/Linux/All#Permission", portName));
            }
            throw new SerialMonitorException(String.format("Error opening serial port ''%s''.", portName), e);
        }

        if (port == null) {
            throw new SerialMonitorException(String.format("Serial port ''%s'' not found. Did you select the right one from the Tools > Serial Port menu?", portName));
        }
    }

    @Override
    public void close() {
        if (port != null) {
            try {
                if (port.isOpened()) {
                    port.closePort();  // close the port
                }
            } catch (SerialPortException e) {
                throw new SerialMonitorException(e.getMessage(), e);
            } finally {
                port = null;
            }
        }
    }

    @Override
    public String read() {
        try {
            byte[] buf = port.readBytes();
            if (buf!= null && buf.length > 0) {
                return new String(buf);
            } else {
                return "";
            }
        } catch (SerialPortException e) {
            throw new SerialMonitorException(e.getMessage(), e);
        }
    }

    private class MySerialPortEventListener implements SerialPortEventListener {
        @Override
        public void serialEvent(SerialPortEvent serialEvent) {
            if (serialEvent.isRXCHAR()) {

            }
        }
    }
}
