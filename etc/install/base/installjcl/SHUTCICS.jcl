//*********************************************************************
//*                                                                   *
//*   Copyright IBM Corp. 2022                                        *
//*                                                                   *
//*********************************************************************
//SHUTCICS JOB ,,CLASS=A,MSGCLASS=H,
//  NOTIFY=&SYSUID,
//  MSGLEVEL=(1,1),REGION=0M  
//*
//STEP0001 EXEC PGM=IKJEFT01,TIME=1440,REGION=0M
//SYSTSPRT DD DUMMY
//SYSTSIN  DD *
CONSOLE NAME(MV1) SYSCMD(-
C CICSTS56)
//*