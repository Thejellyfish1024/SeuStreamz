package bd.edu.seu.seustreamz.interfaces;

import bd.edu.seu.seustreamz.models.Packages;

import java.util.List;

public interface PackageInterface {
    void insertPackage(Packages newPackage);
    List<Packages> getPackages();
    void updatePackage(Packages newPackage);
    Packages getPackage(String packageName);

}
