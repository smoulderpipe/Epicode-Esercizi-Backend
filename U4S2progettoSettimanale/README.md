# Catalogo Bibliografico

Questo progetto Java implementa un catalogo bibliografico che consente di gestire libri e riviste attraverso operazioni di ricerca, rimozione e aggiunta. Il catalogo è salvato su file per consentire il ripristino dei dati anche dopo la chiusura dell'applicazione.

## Funzionalità Principali

- **Ricerca**: È possibile cercare elementi del catalogo per ISBN, autore o anno di pubblicazione.
- **Rimozione**: Gli elementi del catalogo possono essere rimossi tramite l'ISBN. 
- **Aggiunta**: È consentito aggiungere nuovi libri e riviste al catalogo. 
- **Persistenza**: Il catalogo può essere salvato e caricato da file per mantenere i dati anche tra le esecuzioni.

## Requisiti

Per eseguire questo progetto è necessario avere installato:
- Java Development Kit (JDK): versione 11 o superiore.
- Apache Maven: per la gestione delle dipendenze e la compilazione del progetto.

## Struttura del Progetto

Il progetto è organizzato come segue:
 * *src/main/java*: Contiene il codice sorgente Java.
* *persistence*: Contiene file di risorse, come i file di testo per il salvataggio e il caricamento del catalogo.
* *pom.xml*: Il file di configurazione Maven che definisce la struttura del progetto e le dipendenze.

## Esecuzione del Progetto

Il progetto può essere eseguito utilizzando la classe `GestisciCatalogo`.

## Dipendenze
Questo progetto utilizza la seguente dipendenza esterna:
* *Apache Commons IO*: Utilizzato per semplificare le operazioni di lettura e scrittura su file.

## Autore

- [@smoulderpipe](https://github.com/smoulderpipe)