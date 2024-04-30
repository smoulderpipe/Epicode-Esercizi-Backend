select * from public.studenti;

select * from public.studenti where nome='Sandra' or cognome='Verdi';

--LIKE -> per cercare tramite caratteri speciali (nomi che cominciano per, finiscono per, contengono...)
select * from public.studenti where nome like '_a%';

--IN -> per cercare nomi specifici nel database, ed evitare di cercare tramite nome = 'Roberto' or 'Sara'
select * from public.studenti where nome in('Sandra', 'Roberto', 'Sara');

--NOT -> per filtrare i risultati che NON sono come quello specificato nella query
select * from public.studenti where nome not like 'Sandra';

--IS -> per filtrare i risultati che sono X, esempio sono null
select * from public.studenti where data_nascita is null;

--JOIN -> per estrarre dati da più tabelle, nel caso in cui ci servano info da tabelle differenti
--(es nome dello studente che ha fatto un certo esame)
--TIPI DI JOIN: INNERJOIN, OUTERIJOIN (LEFT JOIN, RIGHT JOIN, FULL JOIN)
--"public.esami.studente=public.studenti.matricola" è la condizione di JOIN, permette di collegare la foreign key...
--...di una tabella con la primary key di un'altra tabella
--esempio di inner join:
select nome, cognome, corso, voto
from public.studenti, public.esami
where public.esami.studente=public.studenti.matricola;

--esempio di uso di alias
select s.nome, s.cognome, e.corso, e.voto
from public.studenti s, public.esami e
where e.studente=s.matricola;

--esempio di inner join esplicita
select s.nome, s.cognome, e.corso, e.voto
from public.studenti s inner join public.esami e
on e.studente=s.matricola;

--ordinamento crescente
select * from public.studenti order by nome;

--ordinamento decrescente
select * from public.studenti order by nome desc;

--il distinct serve per non mostrare i duplicati (es. ci sono 2 Fausto con diverso)
select distinct nome from public.studenti;

--COUNT (conta i record)
--Se si usa * seleziona le righe (conteggia anche le null)
select count(*) from public.studenti;
--se si usa "data_nascita" restituisce il numero di celle piene corrispondenti a "data_nascita" (non conta le null)
select count(data_nascita) from public.studenti;

--MAX trova i valori massimi, si possono solo mettere tipi numerici tra parentesi tonde (e non l'asterisco)
select max(matricola) from public.studenti;

--GROUP BY es. "raggruppa per nome gli studenti"
select nome, count(*)
from public.studenti
--where nome='Laura'
group by nome
having count(*)>1;

--conoscere numero esami di ogni studente
select s.matricola, count(*)
from public.studenti s inner join public.esami e
on s.matricola=e.studente
group by matricola;

select *
from public.studenti s left outer join public.esami e
on s.matricola=e.studente;

select *
from public.studenti s right outer join public.esami e
on s.matricola=e.studente;