import 'package:lab/components/ship/ship.dart';

class Cell {
  bool isOccupied;
  Ship? ship;

  Cell({required this.isOccupied, this.ship});
}