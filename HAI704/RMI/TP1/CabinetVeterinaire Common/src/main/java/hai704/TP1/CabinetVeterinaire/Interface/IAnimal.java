package hai704.TP1.CabinetVeterinaire.Interface;

//import hai704.TP1.CabinetVet.internal.models.Specy;

import hai704.TP1.CabinetVeterinaire.Classes.Specy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {

    String getFullName() throws  RemoteException;

     String getName() throws  RemoteException;

    boolean updateName(String name) throws  RemoteException;

    String getMasterName() throws  RemoteException;

    boolean updateMasterName(String masterName) throws  RemoteException;

     String getBreed() throws  RemoteException;

    boolean updateBreed(String breed) throws  RemoteException;

//    String getSpecy() throws  RemoteException;
     Specy getSpecy() throws  RemoteException;
    String getSpecyName() throws  RemoteException;

    Double getAverageLifeSpanInDays(String specyName) throws RemoteException;


    Double getAverageLifeSpanInDays() throws RemoteException;


     String getFollowUpFileContent() throws  RemoteException;
    boolean setFollowUpFileContent(String content) throws  RemoteException;



    String getFullNameTest() throws  RemoteException;
    String getNameTest() throws  RemoteException;
    boolean updateNameTest(String name) throws  RemoteException;
    String getMasterNameTest() throws  RemoteException;
    boolean updateMasterNameTest(String masterName) throws  RemoteException;
    String getBreedTest() throws  RemoteException;
    boolean updateBreedTest(String breed) throws  RemoteException;
    Specy getSpecyTest() throws  RemoteException;
    String getSpecyNameTest() throws  RemoteException;
    Double getAverageLifeSpanInDaysTest(String specyName) throws RemoteException;
    Double getAverageLifeSpanInDaysTest() throws RemoteException;

    // 1-Q3. feature
    String getFollowUpFileContentTest() throws  RemoteException;
    boolean setFollowUpFileContentTest(String content) throws  RemoteException;
    // 1-Q3. feature



}
