//*
//* Copyright IBM Corp. 2023       
//*
//TMP       JOB ,CLASS=A,MSGCLASS=H,NOTIFY=&SYSUID
//*
//*CICS   PROC MEMBER=
//COBOL  EXEC PGM=IGYCRCTL,REGION=0M,
//            PARM=(NODYNAM,LIB,RENT,LIST,MAP,XREF,OPT,
//   'APOST,TRUNC(OPT),CICS')
//STEPLIB  DD DSN=IGY630.SIGYCOMP,DISP=SHR
//         DD DISP=SHR,DSN=DFH560.CICS.SDFHLOAD
//         DD DISP=SHR,DSN=DSND10.SDSNLOAD
//SYSLIB   DD DISP=SHR,DSN=CBSA.CICSBSA.DSECT
//         DD DISP=SHR,DSN=CEE.SCEESAMP
//         DD DISP=SHR,DSN=DFH560.CICS.SDFHCOB
//         DD DISP=SHR,DSN=DFH560.CICS.SDFHSAMP
//         DD DISP=SHR,DSN=CEE.SCEESAMP
//DBRMLIB   DD DISP=SHR,
//          DSN=CBSA.CICSBSA.DBRM(INQACCCU)
//SYSPRINT DD SYSOUT=*
//DFHPRINT DD SYSOUT=*
//SYSOUT   DD SYSOUT=*
//SYSLIN DD DDNAME=OUT
//OUT       DD DISP=SHR,
//          DSN=CBSA.CICSBSA.CBSAMOD(INQACCCU)
//SYSUT1   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT2   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT3   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT4   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT5   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT6   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT7   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT8   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT9   DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT10  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT11  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT12  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT13  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT14  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSUT15  DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSMDECK DD UNIT=SYSALLDA,SPACE=(TRK,(350,100))
//SYSIN    DD DISP=SHR,DSN=CBSA.CICSBSA.COBOL(INQACCCU)
//COPYLINK EXEC PGM=IEBGENER,COND=(7,LT,COBOL)
//SYSUT1   DD *
/*
//SYSUT2   DD DSN=&&COPYLINK,DISP=(NEW,PASS),
//            DCB=(LRECL=80,BLKSIZE=400,RECFM=FB),
//            UNIT=SYSDA,SPACE=(400,(20,20))
//SYSPRINT DD SYSOUT=*
//SYSIN    DD DUMMY
//*
//*
//LKED   EXEC PGM=IEWL,REGION=4M,
//          PARM=(XREF,LIST,LET,RENT,'AMODE(31)','RMODE(ANY)'),
//          COND=(7,LT,COBOL)
//SYSLIB   DD DISP=SHR,DSN=DFH560.CICS.SDFHLOAD
//         DD DISP=SHR,DSN=DSND10.SDSNLOAD
//         DD DSN=CEE.SCEELKED,DISP=SHR
//SYSUT1   DD UNIT=SYSDA,DCB=BLKSIZE=1024,
//            SPACE=(1024,(200,20))
//SYSPRINT DD SYSOUT=*
//CBSAMOD   DD DISP=SHR,
//         DSN=CBSA.CICSBSA.CBSAMOD
//SYSLMOD  DD DISP=SHR,DSN=CBSA.CICSBSA.LOADLIB
//SYSDEFSD DD DUMMY ()
//SYSLIN   DD DDNAME=IN
//IN    DD DISP=SHR,DSN=CBSA.CICSBSA.LKED(INQACCCU)
//*PEND