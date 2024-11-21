import 'package:flutter/material.dart';

class GameFieldWidget extends StatefulWidget {
  const GameFieldWidget({super.key});

  @override
  State<GameFieldWidget> createState() => _GameFieldWidgetState();
}

class _GameFieldWidgetState extends State<GameFieldWidget> {

  @override
  Widget build(BuildContext context) {
    return SizedBox(
        width: 350, // 10 cells * 10px each
        height: 350, // 10 cells * 10px each
        child: CustomPaint(
          painter: MatrixPainter(),
        ),
    );
  }
}
class MatrixPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.black
      ..style = PaintingStyle.stroke
      ..strokeWidth = 1;

    // Размер клетки
    const double cellSize = 35;

    // Рисуем сетку
    for (double x = 0; x <= size.width; x += cellSize) {
      canvas.drawLine(Offset(x, 0), Offset(x, size.height), paint);
    }

    for (double y = 0; y <= size.height; y += cellSize) {
      canvas.drawLine(Offset(0, y), Offset(size.width, y), paint);
    }
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    return true;
  }
}
