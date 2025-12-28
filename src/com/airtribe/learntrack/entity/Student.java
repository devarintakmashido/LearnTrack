package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    // Constructor (with email)
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email); // calling the Person constructor
        this.batch = batch;
        this.active = true;
    }

    // Constructor overloading (without the email)
    public Student(int id, String firstName, String lastName, String batch) {
        this(id, firstName, lastName, "NA", batch);
    }

    // Method overriding (Polymorphism)
    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName();
    }

    public String getBatch() {
        return batch;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }
}
