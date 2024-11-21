import 'package:flutter/material.dart';
import 'package:lab/components/ship/warship_painter.dart';

class DraggableShip extends StatefulWidget {
  final Ship ship;

  const DraggableShip({
    Key? key,
    required this.ship,
  }) : super(key: key);

  @override
  _DraggableShipState createState() => _DraggableShipState();
}

class _DraggableShipState extends State<DraggableShip> {
  @override
  Widget build(BuildContext context) {
    return Draggable(
      data: widget.ship,
      feedback: CustomPaint(
        size: const Size(100, 100),
        painter: WarshipPainterWidget(ship: widget.ship),
      ),
      onDragStarted: () {
        setState(() {
          widget.ship.active = !widget.ship.active;
        });
      },
      child: CustomPaint(
        size: const Size(100, 100),
        painter: WarshipPainterWidget(ship: widget.ship),
      ),
    );
  }
}
