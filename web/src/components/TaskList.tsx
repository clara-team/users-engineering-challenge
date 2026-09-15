import type { Me } from '../api';

type Props = { me: Me };

export function TaskList({ me }: Props) {
  // TODO: Implement task list
  //
  // 1. Call fetchTasks(api) on mount; handle loading and error states.
  // 2. Render each task as a <TaskCard task={task} me={me} />.
  // 3. If me.roles.includes('admin'), render a <CreateTask me={me} onCreated={refresh} />.
  //    Do NOT render it for members — it must be absent from the DOM, not hidden with CSS.
  // 4. Add pagination controls if the page has more than one page.
  //
  // Hint: const api = useApi();

  return <p>TODO: implement TaskList (company: {me.companyId})</p>;
}
