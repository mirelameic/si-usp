import 'package:flutter/material.dart';
import 'package:rp_project/routes.dart';
import 'package:rp_project/widgets/input-text-field.dart';
import 'package:rp_project/widgets/nav-drawer.dart';
import 'package:rp_project/widgets/text-area.dart';

class NewCustomRoadmapCreateTopic extends StatefulWidget {
  @override
  State<NewCustomRoadmapCreateTopic> createState() =>
      _NewCustomRoadmapCreateTopicState();
}

class _NewCustomRoadmapCreateTopicState
    extends State<NewCustomRoadmapCreateTopic> {
  final _topicNameController = TextEditingController();

  void _gotoTopicScreen(context) {
    Navigator.of(context).pop();
  }

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    _topicNameController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(),
      appBar: AppBar(title: Text('Criar tópico customizado')),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          SizedBox(
            height: 10,
          ),
          Padding(
            padding: const EdgeInsets.all(15),
            child: InputTextField(
              controller: _topicNameController,
              keyboardType: TextInputType.text,
              labelText: 'Nome do tópico',
              visiblePassword: true,
            ),
          ),
          TextAreaWidget(
            labelText: 'Qual o conteúdo teórico deste tópico?',
            maxLines: 8,
          ),
          TextAreaWidget(
            labelText:
                'Materias complementares(links de artigos, videos, palestras e etc)',
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
