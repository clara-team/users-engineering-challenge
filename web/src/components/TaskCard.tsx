import type { Me, Task } from '../api';

type Props = { task: Task; me: Me };

export function TaskCard({ task, me }: Props) {
  // TODO: Implement task card
  //
  // Display: title, description, priority, current status, assigneeId (or "Unassigned").
  //
  // Show a <StatusPicker> only when the caller is allowed to update status:
  //   - admin: any task in the company
  //   - member: only tasks where task.assigneeId === me.subject
  //
  // If me.roles.includes('admin'), also show a delete button that calls deleteTask(api, task.id).

  return <div>TODO: implement TaskCard — {task.title} [{task.status}]</div>;
}
