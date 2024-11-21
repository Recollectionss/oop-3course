class Ship{
   late int shipSize;
   late bool isHorizontal;

   Ship({
     required this.shipSize,
     required this.isHorizontal
   });

   get size => shipSize;
   get position => isHorizontal;
}