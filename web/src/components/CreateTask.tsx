import type { Me } from '../api';

type Props = { me: Me; onCreated: () => void };

export function CreateTask({ onCreated }: Props) {
  // TODO: Implement create task form — admin only.
  //
  // Fields:
  //   - title (required, text input)
  //   - description (optional, textarea)
  //   - priority (required, select: LOW / MEDIUM / HIGH)
  //   - assigneeId (optional, text input — Auth0 subject of the assignee)
  //
  // On submit: call createTask(api, { title, description, priority, assigneeId }).
  // On success: call onCreated() to trigger a list refresh.
  // On error: display the error message inline.
  //
  // This component is rendered only for admins (parent checks me.roles).
  // No role check needed inside this component.

  return <div>TODO: implement CreateTask form</div>;
}
