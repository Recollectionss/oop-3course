import 'package:flutter/material.dart';
import 'package:lab/components/button/button.dart';
import 'package:lab/components/cell/cell.dart';
import 'package:lab/components/matrix/matrix_paiter.dart';
import 'package:lab/components/ship/ship.dart';
import 'package:lab/constants/constants.dart';

class BattlePreparationScreen extends StatefulWidget {
  const BattlePreparationScreen({super.key});

  @override
  State<BattlePreparationScreen> createState() => _BattlePreparationScreenState();
}

class _BattlePreparationScreenState extends State<BattlePreparationScreen> {
  final List<List<Cell>> matrix = List.generate(
    10,
        (_) => List.generate(10, (_) => Cell(isOccupied: false, ship: null)),
  );

  final List<Ship> ships = [
    Ship(size: 4, count: 1),
    Ship(size: 3, count: 2),
    Ship(size: 2, count: 3),
    Ship(size: 1, count: 4),
  ];

  Ship? currentShip;

  bool isHorizontal = true;

  @override
  void initState() {
    super.initState();
    currentShip = ships.firstWhere((ship) => ship.count > 0, orElse: () => Ship(size: 0, count: 0));
  }

  bool placeShip(int startX, int startY) {
    if (currentShip == null) return false;

    if (isPlacementValid(startX, startY, currentShip!.size, isHorizontal)) {
      setState(() {
        for (int i = 0; i < currentShip!.size; i++) {
          if (isHorizontal) {
            matrix[startX][startY + i].isOccupied = true;
            matrix[startX][startY + i].ship = currentShip;
          } else {
            matrix[startX + i][startY].isOccupied = true;
            matrix[startX + i][startY].ship = currentShip;
          }
        }

        if (currentShip!.count > 0) {
          currentShip!.count--;
        }

        if (currentShip!.count == 0) {
          currentShip = ships.firstWhere(
                (ship) => ship.count > 0,
            orElse: () => Ship(size: 0, count: 0),
          );
        }
      });
      return true;
    }

    return false;
  }

  bool isPlacementValid(int startX, int startY, int shipSize, bool horizontal) {
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

  void toggleOrientation() {
    setState(() {
      isHorizontal = !isHorizontal;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 9, 41, 104),
      ),
      backgroundColor: Colors.white,
      body: Center(
        child: Column(
          children: [
            const SizedBox(height: 80),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(
                  child: ElevatedButton(
                    onPressed: toggleOrientation,
                    child: Text(isHorizontal ? "Rotate to vertical":"Rotate to horizontal"),
                  ),
                ),
                const SizedBox(width: 20),
                Expanded(
                  child: Text(
                    'Current ship: ${currentShip?.size ?? 0}x, left: ${currentShip?.count ?? 0}',
                    style: const TextStyle(fontSize: 18),
                    textAlign: TextAlign.center,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 20),
            SizedBox(
              width: 350, // 10 cells * 35px each
              height: 350, // 10 cells * 35px each
              child: Stack(
                children: [
                  CustomPaint(
                    size: Size(350, 350),
                    painter: MatrixPainter(),
                  ),
                  ...List.generate(10, (rowIndex) {
                    return List.generate(10, (colIndex) {
                      return Positioned(
                        left: colIndex * 35.0,
                        top: rowIndex * 35.0,
                        child: GestureDetector(
                          onTap: () {
                            placeShip(rowIndex, colIndex);
                          },
                          child: Container(
                            width: 35.0,
                            height: 35.0,
                            decoration: BoxDecoration(
                              border: Border.all(color: Colors.black),
                              color: matrix[rowIndex][colIndex].isOccupied
                                  ? Colors.blue.withOpacity(0.5)
                                  : Colors.transparent,
                            ),
                          ),
                        ),
                      );
                    });
                  }).expand((element) => element).toList(),
                ],
              ),
            ),
            const SizedBox(height: 24),
            CustomButtonWidget(name: "Continue", route: Constants.BATLLE)
          ],
        ),
      ),
    );
  }
}
