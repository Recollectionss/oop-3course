import 'package:flutter/material.dart';
import 'package:lab/models/cell/cell.dart';
import 'package:lab/computer_player/computer_player.dart';
import 'package:lab/computer_player/strategy/game_strategy.dart';
import 'package:lab/models/ship/ship.dart';

class BattleScreen extends StatefulWidget {
  final List<List<Cell>> playerMatrix;
  final GameStrategy computerStrategy;

  const BattleScreen({
    required this.playerMatrix,
    required this.computerStrategy,
    super.key,
  });

  @override
  State<BattleScreen> createState() => _BattleScreenState();
}

class _BattleScreenState extends State<BattleScreen> {
  late List<List<Cell>> playerMatrix;
  late ComputerPlayer computerPlayer;
  bool isPlayerTurn = true;

  @override
  void initState() {
    super.initState();
    playerMatrix = widget.playerMatrix;
    computerPlayer = ComputerPlayer(
      strategy: widget.computerStrategy,
      userMatrix: playerMatrix,
    );
  }

  void handleShot(int row, int col) {
    if (isPlayerTurn && _makeShot(computerPlayer.matrix, row, col)) {
      setState(() {
        isPlayerTurn = false;
      });
      Future.delayed(const Duration(seconds: 1), computerTurn);
    }
  }

  bool _makeShot(List<List<Cell>> matrix, int row, int col) {
    final cell = matrix[row][col];
    if (cell.isShot) return false;

    setState(() {
      cell.isShot = true;
      cell.color = cell.isOccupied ? Colors.red : Colors.grey;

      if (cell.isOccupied && _isShipDestroyed(matrix, cell.ship)) {
        _markSurroundingCells(matrix, cell.ship!); // Перекрашиваем клетки вокруг уничтоженного корабля
      }
    });
    return !cell.isOccupied;
  }

  void computerTurn() {
    final move = computerPlayer.makeMove();
    if (_makeShot(playerMatrix, move.x, move.y)) {
      setState(() {
        isPlayerTurn = true;
      });
    } else {
      Future.delayed(const Duration(seconds: 1), computerTurn);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(isPlayerTurn ? "Ваш ход" : "Ход компьютера"),
        backgroundColor: const Color.fromARGB(255, 9, 41, 104),
      ),
      backgroundColor: Colors.white,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text("Ваше поле"),
          _buildMatrix(playerMatrix, false),
          const SizedBox(height: 20),
          const Text("Поле компьютера"),
          _buildMatrix(computerPlayer.matrix, true),
        ],
      ),
    );
  }

  Widget _buildMatrix(List<List<Cell>> matrix, bool isClickable) {
    const double cellSize = 30.0; // Увеличенный размер ячеек
    return Center(
      child: Container(
        width: cellSize * matrix.length,
        height: cellSize * matrix.length,
        decoration: BoxDecoration(
          border: Border.all(color: Colors.black, width: 2.0), // Граница матрицы
        ),
        child: Stack(
          children: List.generate(matrix.length, (row) {
            return List.generate(matrix.length, (col) {
              return Positioned(
                left: col * cellSize,
                top: row * cellSize,
                child: GestureDetector(
                  onTap: isClickable && isPlayerTurn
                      ? () => handleShot(row, col)
                      : null,
                  child: Container(
                    width: cellSize,
                    height: cellSize,
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.black), // Граница ячеек
                      color: _getCellColor(matrix[row][col], isClickable),
                    ),
                  ),
                ),
              );
            });
          }).expand((i) => i).toList(),
        ),
      ),
    );
  }

  Color _getCellColor(Cell cell, bool isPlayerMatrix) {
    if (cell.isShot) {
      return cell.isOccupied ? Colors.red : Colors.grey;
    } else if (cell.isOccupied && isPlayerMatrix) {
      return Colors.blue.withOpacity(0.5); // Отображение кораблей игрока
    }
    return Colors.transparent;
  }

  bool _isShipDestroyed(List<List<Cell>> matrix, Ship? ship) {
    if (ship == null) return false;
    // Проверяем, уничтожены ли все клетки корабля
    return matrix.every((row) =>
        row.every((cell) => cell.ship != ship || cell.isShot));
  }

  void _markSurroundingCells(List<List<Cell>> matrix, Ship ship) {
    for (final cell in _getShipCells(matrix, ship)) {
      for (int dr = -1; dr <= 1; dr++) {
        for (int dc = -1; dc <= 1; dc++) {
          final row = cell.row + dr;
          final col = cell.col + dc;

          if (_isInBounds(row, col) && !_isOccupied(matrix, row, col)) {
            setState(() {
              matrix[row][col].isShot = true;
              matrix[row][col].color = Colors.grey; // Перекрашиваем клетки вокруг
            });
          }
        }
      }
    }
  }

  List<Cell> _getShipCells(List<List<Cell>> matrix, Ship ship) {
    return matrix.expand((row) => row).where((cell) => cell.ship == ship).toList();
  }

  bool _isInBounds(int row, int col) => row >= 0 && row < 10 && col >= 0 && col < 10;

  bool _isOccupied(List<List<Cell>> matrix, int row, int col) =>
      matrix[row][col].ship != null;
}



