package com.aninfo.model;

import java.util.Date;

public class Tarea {

    private int task_id;
    private String name;
    private String description;
    private long employee_id;
    private Date created_at;
    private Date start_date;
    private Date end_date;
    private String priority;
    private String status;

    public Tarea(int task_id, String name, String description, long employee_id, Date created_at, Date start_date, Date end_date, String priority, String status) {
        this.task_id = task_id;
        this.name = name;
        this.description = description;
        this.employee_id = employee_id;
        this.created_at = created_at;
        this.start_date = start_date;
        this.end_date = end_date;
        this.priority = priority;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
