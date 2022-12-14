//*********************************************************************
//*                                                                   *
//*   Copyright IBM Corp. 2022                                        *
//*                                                                   *
//*********************************************************************
//RACF001 JOB ,,CLASS=A,MSGCLASS=H,
//  NOTIFY=&SYSUID,
//  MSGLEVEL=(1,1)
//*
//TSO     EXEC PGM=IKJEFT01,DYNAMNBR=20,REGION=0M
//SYSTSPRT DD SYSOUT=*
//PROFILES DD SYSOUT=*
//SYSPRINT DD SYSOUT=*
//SYSTSIN  DD *
RDEFINE FACILITY DFHDB2.AUTHTYPE.HBANK OWNER(IBMUSER) UACC(NONE)
PERMIT DFHDB2.AUTHTYPE.HBANK CLASS(FACILITY) -
       ID(CICSUSER IBMUSER JCOLLET OGRADYJ) ACCESS(READ)
RDEFINE FACILITY DFHDB2.AUTHTYPE.DBCG  OWNER(IBMUSER) UACC(NONE)
PERMIT DFHDB2.AUTHTYPE.DBCG  CLASS(FACILITY) -
       ID(CICSUSER IBMUSER JCOLLET OGRADYJ) ACCESS(READ)
SETR RACLIST(FACILITY) REFRESH