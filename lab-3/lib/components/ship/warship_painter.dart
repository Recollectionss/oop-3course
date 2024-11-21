import 'package:flutter/material.dart';

class WarshipPainterWidget extends CustomPainter {
  final Ship ship;

  WarshipPainterWidget({
    required this.ship,
  });

  @override
  void paint(Canvas canvas, Size size) {
    final Paint paint = Paint()
      ..color = (ship.active) ? Colors.green : Colors.black
      ..style = PaintingStyle.fill;

    const double cellSize = 35.0;
    double width = ship.horizontal ? ship.shipSize * cellSize : cellSize;
    double height = ship.horizontal ? cellSize : ship.shipSize * cellSize;

    Offset position = Offset(size.width / 2, size.height / 2);

    canvas.save();

    if (!ship.horizontal) {
      canvas.translate(position.dx, position.dy);
      canvas.rotate(90 * 3.14159 / 180);
      canvas.translate(-position.dx, -position.dy);

      double temp = width;
      width = height;
      height = temp;
    }

    canvas.drawRect(
      Rect.fromLTWH(
        position.dx - width / 2,
        position.dy - height / 2,
        width,
        height,
      ),
      paint,
    );

    canvas.restore();
  }

  @override
  bool shouldRepaint(covariant WarshipPainterWidget oldDelegate) {
    return oldDelegate.ship != ship;
  }
}
class Ship{
  int shipSize;
  bool horizontal;
  bool active;

  Ship({
    required this.shipSize,
    required this.horizontal,
    this.active = true
  });
}

