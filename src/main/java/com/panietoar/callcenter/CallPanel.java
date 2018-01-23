package com.panietoar.callcenter;

import com.panietoar.callcenter.dispatch.Dispatcher;
import com.panietoar.callcenter.model.Call;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


public class CallPanel {

    private static final int MAX_CALLS = 10;

    List<Call> callQueue;
    Dispatcher callDispatcher;

    @FXML
    protected Button callButton;

    @FXML
    private Label labelOperators;

    @FXML
    private Label labelSupervisors;

    @FXML
    private Label labelDirectors;

    @FXML
    private TextArea areaMessages;


    @FXML
    void initialize() {
        callQueue = new ArrayList<>(MAX_CALLS);
        callDispatcher = new Dispatcher(callQueue, Executors.newFixedThreadPool(MAX_CALLS));
    }

    @FXML
    void makeCall(ActionEvent event) {
        callDispatcher.dispatchCall();
    }
}