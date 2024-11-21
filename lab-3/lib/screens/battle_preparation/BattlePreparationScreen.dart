import 'package:flutter/foundation.dart' show kDebugMode;
import 'package:flutter/material.dart';

import '../../components/logo/logo.dart';

class BattlePreparationScreen extends StatefulWidget {
  const BattlePreparationScreen({super.key});

  @override
  State<BattlePreparationScreen> createState() => _BattlePreparationScreenState();
}

class _BattlePreparationScreenState extends State<BattlePreparationScreen> {


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor:Colors.white,
      appBar: AppBar(
        backgroundColor: Color.fromARGB(255, 9, 41, 104),
      ),
      body: Center(
        child: Builder(
          builder: (context) {
            return const Column(
                children: [
                  LogoImageWidget(),
                  ]
            );
          },
        ),
      ),
    );
  }
}