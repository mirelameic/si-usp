import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';
import 'package:rp_project/widgets/date-picker.dart';
import 'package:rp_project/widgets/nav-drawer.dart';

class NewCustomRoadmapConfigure extends StatefulWidget {
  @override
  State<NewCustomRoadmapConfigure> createState() =>
      _NewCustomRoadmapConfigureState();
}

class _NewCustomRoadmapConfigureState extends State<NewCustomRoadmapConfigure> {
  void _gotoMyRoadmapScreen(context) {
    Navigator.of(context).pushReplacementNamed(Routes.myRoadmapScreen);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Configurar roadmap')),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(
            height: 10,
          ),
          Center(
            child: DatePicker(
              label: 'Inicio',
            ),
          ),
          Center(
            child: DatePicker(
              label: 'Fim ',
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: ElevatedButton(
              onPressed: () => _gotoMyRoadmapScreen(context),
              child: Text('Continuar'),
            ),
          ),
        ],
      ),
    );
  }
}
