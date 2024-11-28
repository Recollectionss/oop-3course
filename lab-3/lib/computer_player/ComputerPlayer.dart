import 'package:lab/computer_player/dto/coordinates_dto.dart';
import 'package:lab/computer_player/strategy/game_strategy.dart';
import 'package:lab/models/cell/cell.dart';
import 'package:lab/models/ship/ship.dart';

class ComputerPlayer {
  final GameStrategy strategy;
  final List<List<Cell>> matrix = List.generate(
    10,
        (row) => List.generate(10, (col) => Cell(row: row, col: col,isOccupied: false, ship: null)),
  );
  final List<Ship> shipsForComputer = [
    Ship(size: 4, count: 1),
    Ship(size: 3, count: 2),
    Ship(size: 2, count: 3),
    Ship(size: 1, count: 4),
  ];

  ComputerPlayer({required this.strategy,required List<List<Cell>> userMatrix}){
    placeShipsForComputer();
    strategy.initialize(userMatrix);
  }

  CoordinatesDTO makeMove() {
    return strategy.findNextTarget();
  }

  void placeShipsForComputer() {
    for (var ship in shipsForComputer) {
      bool placed = false;

      for(int shipCount=0; shipCount <= ship.count; shipCount++){
          int startX = strategy.random.nextInt(10);
          int startY = strategy.random.nextInt(10);
          bool horizontal = strategy.random.nextBool();

          if (isPlacementValidForComputer(startX, startY, ship.size, horizontal)) {
            for (int i = 0; i < ship.size; i++) {
              if (horizontal) {
                matrix[startX][startY + i].ship = ship;
                matrix[startX][startY + i].isOccupied = true;
              } else {
                matrix[startX + i][startY].ship = ship;
                matrix[startX + i][startY].isOccupied = true;
              }
            }

        }
      }
    }
  }

  bool isPlacementValidForComputer(int startX, int startY, int shipSize, bool horizontal) {
    if (horizontal) {
      if (startY + shipSize > 10) return false;
    } else {
      if (startX + shipSize > 10) return false;
    }

    for (int i = 0; i < shipSize; i++) {
      if (horizontal) {
        if (matrix[startX][startY + i].isOccupied) return false;
      } else {
        if (matrix[startX + i][startY].isOccupied) return false;
      }
    }

    for (int i = -1; i <= shipSize; i++) {
      for (int j = -1; j <= 1; j++) {
        int x = horizontal ? startX + i : startX + j;
        int y = horizontal ? startY + j : startY + i;

        if (x >= 0 && x < 10 && y >= 0 && y < 10 && matrix[x][y].isOccupied) {
          return false;
        }
      }
    }

    return true;
  }
}