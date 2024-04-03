// blur.cpp : Floute une image en niveau de gris

#include <stdio.h>
#include "image_ppm.h"
#include <cmath>
#include <iostream>

using namespace std;

int main(int argc, char* argv[])
{
  char cNomImgLue[250], cNomImgEcrite[250];
  char *bruit = "Bruit.pgm";
  int nH, nW, nTaille;
  double diff;
  if (argc != 3) 
     {
       printf("Usage: ImageOriginale.pgm  ImageInFloue.pgm \n"); 
       exit (1) ;
     }
   
   sscanf (argv[1],"%s",cNomImgLue) ;
   sscanf (argv[2],"%s",cNomImgEcrite);

   OCTET *ImgInOrg, *ImgInBlur, *ImgOut;
   
   lire_nb_lignes_colonnes_image_pgm(cNomImgLue, &nH, &nW);
   nTaille = nH * nW;
  
   allocation_tableau(ImgInOrg, OCTET, nTaille);
   allocation_tableau(ImgInBlur, OCTET, nTaille);
   lire_image_pgm(cNomImgLue, ImgInOrg, nH * nW);
   lire_image_pgm(cNomImgEcrite, ImgInBlur, nH * nW);
   allocation_tableau(ImgOut, OCTET, nTaille);

   double totPixelMoyenne=0;
    int nbPixel=1;

   for (int i=0; i < nTaille; i++)
    ImgOut[i]= ImgInOrg[i];

 for (int i=1; i < nH-1; i++)
   for (int j=1; j < nW-1; j++)
     {
		diff = ImgInOrg[(i-1)*nW+j-1]-ImgInBlur[(i-1)*nW+j-1]+128;
    
       ImgOut[i*nW+j]=diff;
     }

for (int i=1; i < nH-1; i++)
   for (int j=1; j < nW-1; j++)
     {
		
       totPixelMoyenne += ImgOut[i*nW+j];
nbPixel++;
     }
 
 double moyenne = totPixelMoyenne/nbPixel;

 // Variance
 double somDiffInter=0;

 for (int i=1; i < nH-1; i++)
   for (int j=1; j < nW-1; j++)
     {
		
       somDiffInter += (ImgOut[i*nW+j] - moyenne) *(ImgOut[i*nW+j] - moyenne) ;

     }
 double Variance=somDiffInter/nbPixel;


//EcartType

double EcartType = sqrt(Variance);
cout << "moyenne: " << moyenne << endl;
cout << "Variance: " << Variance << endl;
cout << "EcartType: " << EcartType << endl;
// printf("Moyenne: %d", moyenne);
// printf("EcartType: %d", EcartType);
   ecrire_image_pgm(bruit, ImgOut,  nH, nW);
   free(ImgInOrg);free(ImgInBlur);free(ImgOut);
   return 0;
}
