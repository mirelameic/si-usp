import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';
import 'package:rp_project/widgets/input-text-field.dart';
import 'package:rp_project/widgets/nav-drawer.dart';
import 'package:rp_project/widgets/text-area.dart';

class NewCustomRoadmap extends StatefulWidget {
  @override
  State<NewCustomRoadmap> createState() => _NewCustomRoadmapState();
}

class _NewCustomRoadmapState extends State<NewCustomRoadmap> {
  final _roadmapNameController = TextEditingController();

  void _gotoTopicScreen(context) {
    Navigator.of(context).pushNamed(Routes.newCustomRoadmapTopic);
  }

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    _roadmapNameController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Criar roadmap customizado')),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(
            height: 10,
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: InputTextField(
              controller: _roadmapNameController,
              keyboardType: TextInputType.text,
              labelText: 'Nome do roadmap',
              visiblePassword: true,
            ),
          ),
          TextAreaWidget(
            labelText: 'Descrição do roadmap',
            maxLines: 8,
          ),
          TextAreaWidget(
            labelText: 'O que se aprende nesse roadmap?',
            maxLines: 8,
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: ElevatedButton(
              onPressed: () => _gotoTopicScreen(context),
              child: Text('Continuar'),
            ),
          ),
        ],
      ),
    );
  }
}
