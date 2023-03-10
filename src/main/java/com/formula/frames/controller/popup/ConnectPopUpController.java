/*
 * MIT License
 *
 * Copyright (c)2023 Matheus Markies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.formula.frames.controller.popup;

import com.formula.frames.controller.MainFrameController;
import com.formula.serialport.SerialManager;
import com.formula.serialport.SerialReadder;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectPopUpController implements Initializable {

    @FXML
    private ChoiceBox<String> choisebox_serialport;

    @FXML
    private Button connect_button;

    private SerialReadder serialReadder;

    private MainFrameController controller;

    public ConnectPopUpController() {
    }

    public void setSerialReadder(SerialReadder serialReadder) {
        this.serialReadder = serialReadder;
    }

    @FXML
    void onClickInConnectButton(ActionEvent event) {
        try {
            serialReadder = new SerialReadder(choisebox_serialport.getValue(),controller);
            if(serialReadder.connect()) {
                System.out.println("Connect!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> list = SerialManager.getSerialPortNames();
        choisebox_serialport.setItems(FXCollections.observableArrayList(list));
    }

    public void setController(MainFrameController controller) {
        this.controller = controller;
    }
}
