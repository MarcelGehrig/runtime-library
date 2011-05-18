package ch.ntb.inf.deep.runtime.mpc555.demo;

import ch.ntb.inf.deep.runtime.mpc555.Task;

/*
 * Das nachfolgende Programm wurde entwickelt um die auf dem Robotoer
 * verwendeten Sensoren zu testen. Es werden dabei drei Klassen verwendet. 
 * 
 * Die Haupt-Klasse Robi2SensorProximityDemo wird zum Installieren der beiden anderen Klassen
 * verwendet. 
 * �ber die Klasse Robi2SensorProximityDemo_Out werden die Werte aller Sensoren
 * abgefragt und �ber die serielle Schnittstelle auf das TargetLog (USB)
 * ausgegeben. 
 * Die dritte Klasse Robi2SensorProximityDemo_Led schaltet eine Led ein, wenn
 * ein Sensor anspricht. F�r die Sensoren 0 bis 11 wird jeweils eine rote
 * Pattern LED geschaltet. Die restlichen Sensoren sind den Positions Leds und
 * der Center Led zugeordnet.
 * 
 * Beschreibung:
 * 
 * Die Haupt-Klasse Robi2SensorProximityDemo installiert die beiden Klassen Robi2SensorProximityDemo_Out 
 * und Robi2SensorProximityDemo_Led als Task. Wobei �ber die globale Variabel DEBUG das Installieren 
 * von Robi2SensorProximityDemo_Out verhindert werden kann.
 * 
 * Die Klasse Robi2SensorProximityDemo_Out erweitert Task was sicherstellt, dass nach der Installation 
 * die Methode action() periodisch aufgerufen wird. In dieser Methode werden die aktuellen 
 * Werte der Sensoren ausgelesen und �ber die serielle Schnittstelle auf das USBLog ausgegeben.
 * 
 * Die Klasse Robi2SensorProximityDemo_Led erweitert ebenfalls Task. Hierbei wird in der Methode action() 
 * �ber die Konstante Limit ermittelt, wieviele Sensoren momentan angesprochen werden. 
 * Entsprechend werden die Anzahl Leds eingeschaltet .
 */

public class Robi2SensorProximityDemo {
	static final boolean DEBUG = true;
	static final short NoOfSensors = 16;

	private static Robi2SensorProximityDemo_Out outTask;
	private static Robi2SensorProximityDemo_Led readTask;

	static { // Task Initialisierung

		if (DEBUG) {
			outTask = new Robi2SensorProximityDemo_Out();
			outTask.period = 2000;
			Task.install(outTask);
		}

		readTask = new Robi2SensorProximityDemo_Led();
		readTask.period = 100;
		Task.install(readTask);

	}
}