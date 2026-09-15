import type { Task, TaskStatus } from '../api';

type Props = { task: Task; onUpdated: (updated: Task) => void };

// Valid transitions, mirroring the backend state machine.
const TRANSITIONS: Record<TaskStatus, TaskStatus[]> = {
  TODO:        ['IN_PROGRESS'],
  IN_PROGRESS: ['DONE'],
  DONE:        [],
};

export function StatusPicker({ task, onUpdated }: Props) {
  // TODO: Implement status picker
  //
  // 1. Read TRANSITIONS[task.status] to find the available next statuses.
  // 2. If the array is empty (DONE), render nothing or a "Completed" badge.
  // 3. For each valid next status, render a button.
  // 4. On click: call updateTaskStatus(api, task.id, nextStatus).
  //    On success: call onUpdated(updatedTask).
  //    On 422: display "Invalid transition" inline.
  //
  // Note: this component trusts the server as the source of truth.
  // TRANSITIONS is a UX hint to avoid obviously invalid clicks — not a security boundary.

  const nextStatuses = TRANSITIONS[task.status];

  if (nextStatuses.length === 0) {
    return <span>Completed</span>;
  }

  return <div>TODO: implement StatusPicker — current: {task.status}</div>;
}
