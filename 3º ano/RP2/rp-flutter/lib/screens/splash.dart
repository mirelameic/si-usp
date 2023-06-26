import 'dart:async';
import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';

class SplashScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    Timer(
      Duration(seconds: 3),
      () async {
        Navigator.of(context).pushReplacementNamed(Routes.topico);
      },
    );

    return Scaffold(
      body: Container(
        alignment: Alignment.center,
        color: Colors.black,
        child: Stack(
          children: [
            Positioned.fill(
              child: Opacity(
                opacity: 0.3,
                child: Image.asset(
                  'assets/images/pet.jpg',
                  fit: BoxFit.cover,
                ),
              ),
            ),
            Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Center(
                    child: ClipOval(
                      child: Container(
                        width: 180,
                        height: 180,
                        alignment: Alignment.center,
                        child: Image.asset('assets/images/banner1.jpeg'),
                      ),
                    ),
                  ),
                  Text(
                    'Pets Store',
                    style: TextStyle(
                      color: Colors.red,
                      fontSize: 30,
                    ),
                    textAlign: TextAlign.center,
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
