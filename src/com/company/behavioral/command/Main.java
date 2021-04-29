package com.company.behavioral.command;

import java.util.HashMap;
import java.util.Map;

class Editor {
	private static Editor instance = new Editor();
	private String clipboard;
	private String document;

	private Editor(){
		this.document = "";
	}

	public static Editor getInstance(){
		return instance;
	}

	public String getClipboard() {
		return clipboard;
	}

	public void setClipboard(String clipboard) {
		this.clipboard = clipboard;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}

interface Command {
	void setArgs(String args);
	void execute();
}

class Copy {

	private String content;

	public Copy(){

	}


	void setContent(String content){
		this.content = content;
	}

	void copyToClipboard(){
		Editor e = Editor.getInstance();
		e.setClipboard(this.content);
	}
}

class CopyCommand implements Command {
	Copy c;

	public CopyCommand(Copy c){
		this.c = c;
	}

	public void setArgs(String args){
		this.c.setContent(args);
	};

	@Override
	public void execute() {
		c.copyToClipboard();
	}
}

class NoCommand implements Command {

	public void setArgs(String args){

	}

	@Override
	public void execute() {

	}
}

class Application {
	private Map<String, Command> menu = new HashMap<>();

	public Application() {

		this.createCommands();
	}

	private void createCommands(){
		Command noCommand = new NoCommand();
		menu.put("copy", new CopyCommand(new Copy()));
		menu.put("paste", noCommand);
	}

	public void action(String action, String args){
		Command c = menu.get(action);
		c.setArgs(args);
		c.execute();
	}

}

public class Main {
	public static void main(String[] args){
		Application s = new Application();
		s.action("copy", "aaa\r\nefwefw");

		Editor e = Editor.getInstance();
		System.out.println(e.getClipboard());

	}
}
