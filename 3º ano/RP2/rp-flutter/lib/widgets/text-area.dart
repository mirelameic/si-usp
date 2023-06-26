import 'package:flutter/material.dart';

class TextAreaWidget extends StatelessWidget {
  final String labelText;
  final int? minLines;
  final int maxLines;

  TextAreaWidget({
    required this.labelText,
    required this.maxLines,
    this.minLines,
  });

  @override
  Widget build(BuildContext context) {
    return Flexible(
      child: Padding(
        padding: const EdgeInsets.all(15),
        child: TextField(
          keyboardType: TextInputType.multiline,
          minLines: this.minLines != null ? this.minLines : 1,
          maxLines: this.maxLines,
          decoration: InputDecoration(
            contentPadding:
                EdgeInsets.symmetric(vertical: 10.0, horizontal: 20.0),
            border: OutlineInputBorder(),
            label: Text(this.labelText),
          ),
        ),
      ),
    );
  }
}
