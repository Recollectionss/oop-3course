import 'package:flutter/material.dart';

class WarshipPainterWidget extends CustomPainter {
  final Ship ship;

  WarshipPainterWidget({
    required this.ship,
  });

  @override
  void paint(Canvas canvas, Size size) {
    final Paint paint = Paint()
      ..color = Colors.green
      ..style = PaintingStyle.fill;

    const double cellSize = 20.0;
    double width = ship.isHorizontal ? ship.shipSize * cellSize : cellSize;
    double height = ship.isHorizontal ? cellSize : ship.shipSize * cellSize;

    Offset position = Offset(size.width / 2, size.height / 2);

    canvas.save();

    if (!ship.isHorizontal) {
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
  late int shipSize;
  late bool isHorizontal;

  Ship({
    required this.shipSize,
    required this.isHorizontal
  });

  get size => shipSize;
  get position => isHorizontal;
}
