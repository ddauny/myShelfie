# Group_03
**my shelfie - progetto programmazione ad oggetti a.a. 22/23**

**LEGGERE PRIMA DI GIOCARE**

Una volta avviato il gioco vi verrà richiesto, in console, di inserire il numero dei giocatori (che deve essere superiore ad uno ed inferiore o uguale a quattro), successivamente sarà necessario inserire il nome di ogni giocatore.

A questo punto il gioco ha inizio!

Si apriranno due finestre, la prima, *MyShelfie*, mostrerà la plancia di gioco ed i due obiettivi comuni da raggiungere; la seconda finestra, *Oggetti di [nome giocatore]*, conterrà la libreria del giocatore e l’obiettivo personale da perseguire.

Come primo passo del gioco il primo giocatore (il primo che è stato inserito in console precedentemente) deve selezionare la prima tessera che vuole inserire nella sua libreria, al termine della scelta gli verrà chiesto, tramite una finestra di avviso, se desidera continuare a prelevare un’altra tessera (fino ad un massimo di tre tessere) solo se la precedente scelta della tessera lo permette, ovvero se c'è una tessere adiacente che abbia almeno un lato libero. Terminata la selezione delle tessere verrà ricaricata la finestra *Oggetti di [nome giocatore]* nella quale sarà possibile scegliere quale tessera inserire per prima e la posizione nella quale metterla. Con lo stesso procedimento si possono inserire le altre tessere scelte. Terminato il turno del giocatore va chiusa la finestra *Oggetti di [nome giocatore]* così che il giocatore successivo possa procedere a giocare il proprio turno. Al momento della chiusura della suddetta finestra verrà caricata la finestra *Oggetti di [nome giocatore]* del giocatore successivo. Queste operazioni si susseguiranno durante il turno di ogni giocatore.

Durante la scelta della posizione di ogni tessera verrà impedito al giocatore di inserire le tessere scelte in una colonna che non disponga di sufficienti spazi liberi per poterle accogliere tutte.

La plancia rimarrà sempre visibile, la finestra verrà posta in secondo piano durante la scelta della posizione delle tessere in libreria, così da poter controllare in ogni momento l’obiettivo comune da raggiungere.

La fase risolutiva del gioco ha inizio nel momento in cui un giocatore termina la propria libreria. L’effettiva conclusione del gioco avviene solo dopo che ha concluso il proprio turno l’ultimo giocatore. A questo punto verrà mostrata una finestra di avviso in cui si comunica il nome del giocatore. Nella console compariranno i punteggi raggiunti da ogni giocatore.
