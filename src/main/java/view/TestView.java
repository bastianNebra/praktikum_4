package view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.DBZugrief;
import praktikum_4.Artikel;
import praktikum_4.Lieferant;
import praktikum_4.Lieferung;

public class TestView {
	private static int anwort;
	private static List<Artikel> artikels = new ArrayList<>();
	private static List<Lieferant> lieferants = new ArrayList<>();
	private static List<Lieferung> lieferungs = new ArrayList<>();
	private static int min, max;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// PROGRAMM START

		try (Scanner sc = new Scanner(System.in)) {

			print("Willkommen zu unsere Programm\n  ----------------------");
			print("Es gibt Folgende Tabale in unsere Database: \n 1 - Artikel\n 2 - Lieferant\n 3 - Lieferung\n");

			// Unsere Benutzer muss eine von der verfügbaren Tabelle Auswählen
			do {
				print("Womit wollen Sie anfangen, \nACHTUNG ! NUR : \n [1,2,3] Wählen, 1=Artikel, 2=Lieferant, 3=Lieferung");
				anwort = sc.nextInt();

			} while (anwort != 1 & anwort != 2 & anwort != 3);

			// Unserer Benutzer hat die Table Artikel Ausgewähl
			if (anwort == 1) {

				// Wan sie machen können
				do {
					print("Danke, Sie haben die Tabelle Artikel Ausgewähl\n ----Jetzt, "
							+ "was wollen Sie machen---- Sie können :\n 1 - Anlegen\n 2 - Löschen\n "
							+ "3 - Eine Bestimte Artikel Lesen\n 4 - Artikel List aktualisieren\n 5 - Eine Bestimte Artikel mit Lieferung Löchen");
					anwort = sc.nextInt();

				} while (anwort != 1 & anwort != 2 & anwort != 3 & anwort != 4 & anwort != 5);

				// Artikel Anlegen
				if (anwort == 1) {
					print("1 - Artiklen anlegen");

					// Objekt ArtikelDTO erstellen
					@SuppressWarnings({ "deprecation", "unused" })
					Date date = new Date(4, 11, 21);
					Artikel artikel1 = new Artikel();
					Artikel artikel2 = new Artikel();
					Artikel artikel3 = new Artikel();
					Artikel artikel4 = new Artikel();
					Artikel artikel5 = new Artikel();
					Artikel artikel6 = new Artikel();

					// ===========================
					artikels.add(artikel1);
					artikels.add(artikel2);
					artikels.add(artikel3);
					artikels.add(artikel4);
					artikels.add(artikel5);
					artikels.add(artikel6);

					// Alle Artikellöschen
					Artikel.ArtikelLoeschen(DBZugrief.dbConnect(), anwort);
					for (Artikel artikel : artikels) {
						Artikel.ArtikelInsert(DBZugrief.dbConnect(), artikel);

					}
					print("======= " + 5 + " Artikel  wurde Angelegt =======");

					// Artikel Lesen
					artikels = Artikel.findAll(DBZugrief.dbConnect());
					print("Tabelle Artikel");
					for (Artikel artikel : artikels) {
						print("\n" + "Artikel Nummer - " + artikel.getAnr() + ", Artikel Name - "
								+ artikel.getBezeichnung() + ", Preis - " + artikel.getPreis() + "€" + " Date - "
								+ artikel.getAngelegt());
					}

				}

				// Artikel Löchen
				if (anwort == 2) {
					print("2 - Alle Artiklen löschen");
					Artikel.ArtikelAllLoeschen(DBZugrief.dbConnect());
				}

				// Eine Bestimte Artikel Lesen
				if (anwort == 3) {
					print("3 - Eine Bestimte Artikel Lesen\n =====Geben Sie die nummer von der Artikel=====");
					anwort = sc.nextInt();
					if (Artikel.findByIdWithLiferung(DBZugrief.dbConnect(), anwort) != null) {
						Artikel art = (Artikel) Artikel.findByIdWithLiferung(DBZugrief.dbConnect(), anwort);

						print("Lieferung von Diete Artikel: " + "\n" + "Lieferung  - " + art.getLieferungs()
								+ ", Artikel Nummer - " + art.getAnr() + ", Preis - " + art.getPreis());

					} else {
						print("Der gewünschte Artikel existiert nicht");
					}
				}

				// Artikel Aktualisieren
				if (anwort == 4) {
					print("4 - Die Tabelle Artikel aktualisieren");
					artikels = Artikel.LArtikelAktualisieren(DBZugrief.dbConnect());
					print("Tabelle Artikel");
					for (Artikel artikel : artikels) {
						print("\n" + "Artikel Nummer - " + artikel.getAnr() + ", Artikel Name - "
								+ artikel.getBezeichnung() + ", Preis - " + artikel.getPreis() + "€" + " Date - "
								+ artikel.getAngelegt());
					}

				}

				// Eine Bestimte Artikel mit Lieferung Löchen
				if (anwort == 5) {
					print("5 - Eine Bestimte Artikel mit Lieferung Löchen\n ----Geben Sie die Nummer von der Artkel");
					anwort = sc.nextInt();
					Artikel.deleteWithLieferung(DBZugrief.dbConnect(), anwort);
				}
			}

			// Unserer Benutzer hat die Tabele Lieferant Ausgewähl
			if (anwort == 2) {

				// Wan sie machen können
				do {
					print("Danke, Sie haben die Tabelle Lieferant Ausgewähl\n ---- Jetzt, was wollen Sie machen "
							+ "---- Sie können :\n 1 - Anlegen und Aufgeben\n 2 - Löschen\n 3 - Eine Bestimte Lieferant "
							+ "Lesen\n 4 - Lieferant List aktualisieren\n 5 - Lieferanten zwischen 2 gegebenen Lieferantennummen lesen\n 6 - Lieferanten mit Lieferungen Löschen ");
					anwort = sc.nextInt();

				} while (anwort != 1 & anwort != 2 & anwort != 3 & anwort != 4 & anwort != 5 & anwort != 6);

				// Artikel Anlegen
				if (anwort == 1) {
					print("1 - Lieferant anlegen");

					Lieferant l1 = new Lieferant(1, "Müller", "67657");
					Lieferant LIEFERANT2 = new Lieferant(2, "Thomas", "67689");
					Lieferant LIEFERANT3 = new Lieferant(3, "Marko", "68298");
					Lieferant LIEFERANT4 = new Lieferant(4, "Bastian", "67536");
					Lieferant LIEFERANT5 = new Lieferant(5, "Jouel", "64356");
					Lieferant LIEFERANT6 = new Lieferant(6, "Yvan", "61568");

					// ===========================
					lieferants.add(l1);
					lieferants.add(LIEFERANT2);
					lieferants.add(LIEFERANT3);
					lieferants.add(LIEFERANT4);
					lieferants.add(LIEFERANT5);
					lieferants.add(LIEFERANT6);

					// Alle Artikel Anlegen
					Lieferant.deleteWithLieferung(DBZugrief.dbConnect(), anwort);
					for (Lieferant artikel : lieferants) {
						Lieferant.LieferantAnlegen(DBZugrief.dbConnect(), artikel);
					}
					print("======= " + 5 + " Lieferant  wurde Angelegt =======");

					// Artikel Lesen
					artikels = Artikel.findAll(DBZugrief.dbConnect());
					print("Tabelle Lieferant :");
					for (Lieferant lieferant : lieferants) {
						print("\n" + "Lieferant Nummer - " + lieferant.getLnr() + ", Lieferant Name - "
								+ lieferant.getName() + ", Preis - " + lieferant.getPlz());
					}

				}

				/*
				 * // Lieferant Löchen if (anwort == 2) { print("2 - Alle Lieferanten löschen");
				 * if (Lieferant.deleteAll(DBZugrief.dbConnect()) >= 1) { print("======= " + 5 +
				 * " Lieferanten  wurde gelöscht ======="); } else {
				 * print("======= Nicht gelöscht ======="); } }
				 */

				// Eine Bestimte Lieferant Lesen
				if (anwort == 3) {
					print("3 - Eine Bestimte Lieferant Lesen\n =====Geben Sie die nummer von der Lieferant=====");
					anwort = sc.nextInt();

					if (Lieferant.LieferantById(DBZugrief.dbConnect(), anwort) != null) {
						Lieferant lieferant = (Lieferant) Lieferant.LieferantById(DBZugrief.dbConnect(), anwort);
						print(lieferant);

					} else {
						print("Der gewünschte Artikel existiert nicht");
					}
				}

				// Lieferant Aktualisieren
				if (anwort == 4) {
					print("4 - Die Tabelle Lieferant aktualisieren");
					lieferants = Lieferant.LLAktualisieren(DBZugrief.dbConnect());
					print("Tabelle Lieferant :");
					for (Lieferant lieferant : lieferants) {
						print("\n" + "Liefe. Nummer - " + lieferant.getLnr() + ", Liefe. Name - " + lieferant.getName()
								+ ", PLZ - " + lieferant.getPlz());
					}

				}

				// Lieferan zwischen Geben
				if (anwort == 5) {

					do {
						print("5 - Lieferant zischen Zwei gegebenen Lieferantennummern lesen \n Bitte geben Sie die gewunschten Nummer ");
						// Geben
						print("-- Minimal Nummer --");
						min = sc.nextInt();
						print("-- Maximal Nummer --");
						max = sc.nextInt();
					} while (max <= min);

					lieferants = Lieferant.LieferantZwieschenZwei(DBZugrief.dbConnect(), min, max);
					print("Tabelle Lieferant :");
					for (Lieferant lieferant : lieferants) {
						print("\n" + "Liefe. Nummer - " + lieferant.getLnr() + ", Liefe. Name - " + lieferant.getName()
								+ ", PLZ - " + lieferant.getPlz());
					}

				}

				// Eine Bestimte Lieferant mit Lieferung Löchen
				if (anwort == 6) {
					print("6 - Eine Bestimte Lieferant mit Lieferung Löchen\n ----Geben Sie die Nummer von der Lieferant");
					anwort = sc.nextInt();
					Lieferant.deleteWithLieferung(DBZugrief.dbConnect(), anwort);
				}
			}

			// Unserer Benutzer hat die Table Lieferung Ausgewähl
			if (anwort == 3) {

				// Wan sie machen können
				do {
					print("Danke, Sie haben die Tabelle Lieferung ausgewählt\n ----Jetzt, was wollen Sie machen---- Sie können :\n 1 - Anlegen und Aufgeben\n 2 - Löschen\n 3 - Alle Lieferungen zu einer LNr lesen\n 4 - Alle Lieferungen zu einer ANr lesen");
					anwort = sc.nextInt();

				} while (anwort != 1 & anwort != 2 & anwort != 3 & anwort != 4);

				// Lieferung Anllegen
				if (anwort == 1) {
					print("1 - Lieferung anlegen");
					// Objekt ArtikelDTO erstellen
					Lieferung lieferung1 = new Lieferung();
					Lieferung lieferung2 = new Lieferung();
					Lieferung lieferung3 = new Lieferung();
					Lieferung lieferung4 = new Lieferung();
					Lieferung lieferung5 = new Lieferung();

					// ===========================
					lieferungs.add(lieferung1);
					lieferungs.add(lieferung2);
					lieferungs.add(lieferung3);
					lieferungs.add(lieferung4);
					lieferungs.add(lieferung5);

					// Alle Artikellöschen
					Lieferung.LieferungLoeschen(DBZugrief.dbConnect(), anwort);
					for (Lieferung lieferung : lieferungs) {
						Lieferung.LieferungAnlegen(DBZugrief.dbConnect(), lieferung);
					}
					print("======= " + 5 + " Lieferungen  wurde Angelegt =======");

					// Lieferung Lesen
					lieferungs = Lieferung.LieferungLesen(DBZugrief.dbConnect());
					print("Tabelle Lieferung");
					for (Lieferung lieferung : lieferungs) {
						print("\n" + "Lieferung Nummer - " + lieferung.getId() + ", Lieferung Name - "
								+ lieferung.getArtikel() + ", Preis - " + lieferung.getPreis() + "€");
					}
				}

				// Alle Lieferungen zu einer LNr lesen
				if (anwort == 3) {

					print("3 - Alle Lieferungen zu einer LNr lesen\n =====Geben Sie die nummer von der Lieferant=====");
					anwort = sc.nextInt();

					if (Lieferung.LieferungIdLesen(DBZugrief.dbConnect(), anwort) != null) {

						lieferungs = (List<Lieferung>) Lieferung.LieferungIdLesen(DBZugrief.dbConnect(), anwort);
						// Dazu gehörige Lieferungen
						print("Lieferung dieser Liferant : ");
						for (Lieferung lieferung : lieferungs) {
							print("\n" + "Lieferung Nummer - " + lieferung.getId() + ", Artikel Nummer - "
									+ lieferung.getArtikel() + ", Preis - " + lieferung.getPreis() + "€");
						}

					} else {
						print("Der gewünschte Lieferung existiert nicht");
					}

				}
				// Alle Lieferungen zu einer ANr lesen
				if (anwort == 4) {
					print("4 - Alle Lieferungen zu einer ANr lesen\n =====Geben Sie die nummer von der Artikel=====");
					anwort = sc.nextInt();

					if (Lieferung.LieferungIdLesen(DBZugrief.dbConnect(), anwort) != null) {

						lieferungs = (List<Lieferung>) Lieferung.LieferungIdLesen(DBZugrief.dbConnect(), anwort);
						// Dazu gehörige Lieferungen
						print("Lieferung dieser Liferant : ");
						for (Lieferung lieferung : lieferungs) {
							print("\n" + "Lieferung Nummer - " + lieferung.getId() + ", Artikel Nummer - "
									+ lieferung.getArtikel() + ", Preis - " + lieferung.getPreis() + "€");
						}

					} else {
						print("Der gewünschte Lieferung existiert nicht");
					}
				}

			}

			sc.close();
		} catch (Exception e) {
			print(e.getMessage());
		}

	}

	// Function sysout
	public static void print(Object stg) {
		System.out.println(stg);
	}
}
