package com.company.behavioral.state;

// TODO: Task State
//                          Suspended
//                        /             \
//  Create Task -> Ready   ---------->  Running
//                       \             /
//                          Blocked



// TAT - Task Scheduling
// Queued, Running, Failed, Success, Cancel, Callback, Validation,



abstract class State {

    Scheduler scheduler;

    public State(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    abstract void queue();

    abstract void dequeue();

    abstract void suspend();

    abstract void block();
}

class ReadyState extends State {

    ReadyState(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void queue() {
        System.out.println("Set task to running state");
        scheduler.setCurrentTaskState(scheduler.getRunningState());
    }

    @Override
    public void dequeue() {
        System.out.println("There is no task in running");
    }

    @Override
    public void suspend() {
        System.out.println("There is no task in running");
    }

    @Override
    public void block() {
        System.out.println("There is no task in running");
    }
}

class RunningState extends State {

    RunningState(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void queue() {
        System.out.println("New task has high priority more than current task, blocking current task");
        scheduler.setCurrentTaskState(scheduler.getBlockedState());
    }

    @Override
    public void dequeue() {
        System.out.println("Task should be suspended or blocked to move on");
    }

    @Override
    public void suspend() {
        System.out.println("Suspending current task");
        scheduler.setCurrentTaskState(scheduler.getSuspendedState());
    }

    @Override
    public void block() {
        System.out.println("Blocking current task");
        scheduler.setCurrentTaskState(scheduler.getBlockedState());
    }
}

class SuspendedState extends State {

    SuspendedState(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void queue() {
        System.out.println("Task is not ready");
    }

    @Override
    public void dequeue() {
        System.out.println("Setting current task to ready mode");
        scheduler.setCurrentTaskState(scheduler.getReadyState());
    }

    @Override
    public void suspend() {
        System.out.println("Task already suspended");
    }

    @Override
    public void block() {
        System.out.println("Task is not running");
    }
}

class BlockedState extends State {

    BlockedState(Scheduler scheduler) {
        super(scheduler);
    }

    @Override
    public void queue() {
        System.out.println("Task is not ready");
    }

    @Override
    public void dequeue() {
        System.out.println("Settings task to ready mode");
        scheduler.setCurrentTaskState(scheduler.getReadyState());
    }

    @Override
    public void suspend() {
        System.out.println("Task is not running");
    }

    @Override
    public void block() {
        System.out.println("Task is already blocked");
    }
}

class Scheduler {
    private State readyState;
    private State runningState;
    private State blockedState;
    private State suspendedState;

    private State currentTaskState;

    public Scheduler() {
        this.readyState = new ReadyState(this);
        this.runningState = new RunningState(this);
        this.blockedState = new BlockedState(this);
        this.suspendedState = new SuspendedState(this);

        this.currentTaskState = this.readyState;

    }

    public State getCurrentTaskState() {
        return currentTaskState;
    }

    public void setCurrentTaskState(State currentTaskState) {
        this.currentTaskState = currentTaskState;
    }

    public State getReadyState() {
        return readyState;
    }

    public State getRunningState() {
        return runningState;
    }

    public State getBlockedState() {
        return blockedState;
    }

    public State getSuspendedState() {
        return suspendedState;
    }

    void createTask(){

    }

    void queue(){
        currentTaskState.queue();
    }

    void dequeue(){
        currentTaskState.dequeue();
    }

    void suspend(){
        currentTaskState.suspend();
    }

    void block(){
        currentTaskState.block();
    }
}

public class Main {

    public static void main(String[] args) {
        Scheduler s = new Scheduler();
        s.queue();
        s.block();
        s.dequeue();
        s.queue();
        s.suspend();
        s.queue();
        s.dequeue();

    }

}
