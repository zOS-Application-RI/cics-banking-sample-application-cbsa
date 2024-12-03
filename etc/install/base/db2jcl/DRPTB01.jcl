//*
//* Copyright IBM Corp. 2023       
//*
//DRPTB01 JOB 'DB2',NOTIFY=&SYSUID,CLASS=A,MSGCLASS=H,
//          MSGLEVEL=(1,1),REGION=4M
//*
//JCLLIB  JCLLIB ORDER=DSND10.PROCLIB
//JOBLIB  DD  DISP=SHR,DSN=DSND10.SDSNLOAD
//GRANT   EXEC PGM=IKJEFT01,DYNAMNBR=20
//SYSTSPRT DD  SYSOUT=*
//SYSPRINT DD  SYSOUT=*
//SYSUDUMP DD  SYSOUT=*
//SYSTSIN  DD  *
  DSN SYSTEM(DBCG)
  RUN PROGRAM(DSNTEP2)  PLAN(DSNTEP12) -
       LIB('DSND10.DBDG.RUNLIB.LOAD') PARMS('/ALIGN(MID)')
  END
//SYSIN    DD  *
SET CURRENT SQLID = 'IBMUSER';
DROP TABLE ACCOUNT;
/*