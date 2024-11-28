import 'package:flutter/material.dart';
import 'package:lab/computer_player/dto/coordinates_dto.dart';
import 'package:lab/computer_player/strategy/game_strategy.dart';
import 'dart:math';

class MiddleStrategy extends GameStrategy {
  final Set<Offset> visitedCells = {};
  final List<Offset> hitCells = [];
  final Random random = Random();

  @override
  CoordinatesDTO findNextTarget() {
    if (hitCells.isNotEmpty) {
      return _findAroundHits();
    }

    return _generateRandomTarget();
  }

  CoordinatesDTO _generateRandomTarget() {
    int row, col;
    Offset randomTarget;

    do {
      row = random.nextInt(10);
      col = random.nextInt(10);
      randomTarget = Offset(col.toDouble(), row.toDouble());
    } while (visitedCells.contains(randomTarget));

    visitedCells.add(randomTarget);
    return CoordinatesDTO(x: col, y: row);
  }

  CoordinatesDTO _findAroundHits() {
    final directions = [
      Offset(0, 1),  // top
      Offset(1, 0),  // right
      Offset(0, -1), // bottom
      Offset(-1, 0)  // left
    ];

    for (final hit in hitCells) {
      for (final direction in directions) {
        final nextTarget = Offset(hit.dx + direction.dx, hit.dy + direction.dy);

        if (_isValidCell(nextTarget.dy.toInt(), nextTarget.dx.toInt())) {
          visitedCells.add(nextTarget);
          return CoordinatesDTO(x: nextTarget.dx.toInt(), y: nextTarget.dy.toInt());
        }
      }
    }

    hitCells.clear();
    return _generateRandomTarget();
  }

  void registerShotResult(int row, int col, bool isHit, {bool isSunk = false}) {
    final target = Offset(col.toDouble(), row.toDouble());
    visitedCells.add(target);

    if (isHit) {
      hitCells.add(target);

      if (isSunk) {
        _clearSurroundingCells(row, col);
        hitCells.clear();
      }
    }
  }

  void _clearSurroundingCells(int row, int col) {
    const offsets = [
      Offset(-1, -1), Offset(-1, 0), Offset(-1, 1),
      Offset(0, -1), /*  корабль  */ Offset(0, 1),
      Offset(1, -1), Offset(1, 0), Offset(1, 1),
    ];

    for (final offset in offsets) {
      int newRow = row + offset.dy.toInt();
      int newCol = col + offset.dx.toInt();
      final target = Offset(newCol.toDouble(), newRow.toDouble());

      if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 10) {
        visitedCells.add(target);
      }
    }
  }

  bool _isValidCell(int row, int col) {
    return row >= 0 &&
        row < 10 &&
        col >= 0 &&
        col < 10 &&
        !visitedCells.contains(Offset(col.toDouble(), row.toDouble()));
  }
}
