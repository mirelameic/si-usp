import 'package:flutter/material.dart';

class RoadmapProgressWidget extends StatelessWidget {
  final String progress;
  final String checkpoint;

  RoadmapProgressWidget({required this.progress, required this.checkpoint});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 200,
      alignment: Alignment.centerRight,
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Padding(
            padding: const EdgeInsets.only(top: 10, bottom: 10, right: 10),
            child: VerticalDivider(
              thickness: 2,
            ),
          ),
          SizedBox(
            width: 150,
            child: Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text('Completado: $progress'),
                  Text('Última vez: $checkpoint'),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
