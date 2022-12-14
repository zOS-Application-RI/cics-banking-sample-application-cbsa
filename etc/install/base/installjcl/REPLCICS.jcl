//*********************************************************************
//*                                                                   *
//*   Copyright IBM Corp. 2022                                        *
//*                                                                   *
//*********************************************************************
//REPLCICS JOB ,,CLASS=A,MSGCLASS=H,
//  NOTIFY=&SYSUID,
//  MSGLEVEL=(1,1),REGION=0M
//*
//JOBSTEP  EXEC  PGM=IEBCOPY
//SYSPRINT DD  SYSOUT=H
//SYSOUT DD SYSOUT=H
//SYSUT1   DD  DSNAME=CBSA.JCL.INSTALL,
//             DISP=SHR
//SYSUT2   DD  DSNAME=FEU.Z25A.PROCLIB,
//             DISP=(SHR,KEEP)
//SYSUT3   DD  UNIT=SYSDA,SPACE=(TRK,(5))
//SYSUT4   DD  UNIT=SYSDA,SPACE=(TRK,(5))
//SYSIN    DD  *
COPYOPER   COPY OUTDD=SYSUT2
               INDD=SYSUT1
           SELECT MEMBER=((CICSTS56,,R))
/*