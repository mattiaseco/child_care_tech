package client;

import com.github.sarxos.webcam.WebcamPanel;

public interface CheckPointControllerInterface {
    void saveCheckPoint(String code);
    void setPane(WebcamPanel webcamPanel);
}
