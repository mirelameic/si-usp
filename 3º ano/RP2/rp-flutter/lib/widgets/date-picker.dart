import 'package:flutter/material.dart';

class DatePicker extends StatefulWidget {
  final String label;
  const DatePicker({Key? key, required this.label}) : super(key: key);

  @override
  _DatePickerState createState() => _DatePickerState();
}

class _DatePickerState extends State<DatePicker> {
  DateTime? _selectedDate;

  void _pickDateDialog() {
    showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(1950),
      lastDate: DateTime(2101),
    ).then((pickedDate) {
      if (pickedDate == null) {
        return;
      }
      setState(() {
        _selectedDate = pickedDate;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        ElevatedButton(
          child: Text(widget.label),
          onPressed: _pickDateDialog,
        ),
        SizedBox(width: 20),
        Text(
          _selectedDate == null
              ? 'No date was chosen!'
              : 'Picked Date: $_selectedDate',
        ),
      ],
    );
  }
}
