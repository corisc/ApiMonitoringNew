package com.etc.apiMonitor.models;

import javafx.beans.property.*;

import java.time.LocalDate;


public class TestInstance {
    private final StringProperty httpAddress;
    private final StringProperty accountCreated;
    private final IntegerProperty cycle;
    private final ObjectProperty<LocalDate> dateCreated;
    private final StringProperty message;

    public TestInstance(String httpAddress, String accountCreated, int cycle, LocalDate dateCreated, String message) {
        this.httpAddress = new SimpleStringProperty(this, "sender", httpAddress);
        this.accountCreated = new SimpleStringProperty(this, "receiver", accountCreated);
        this.cycle = new SimpleIntegerProperty( this, "amount", cycle);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", dateCreated);
        this.message = new SimpleStringProperty(this, "Message", message);
    }

    public StringProperty httpAddressProperty() {
        return this.httpAddress;
    }

    public StringProperty accountCreatedProperty() {
        return this.accountCreated;
    }

    public IntegerProperty cycleProperty() {
        return this.cycle;
    }

    public ObjectProperty<LocalDate> dateCreatedProperty() {
        return this.dateCreated;
    }

    public StringProperty messageProperty() {
        return this.message;
    }
}
