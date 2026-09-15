import { useAuth0 } from '@auth0/auth0-react';
import { useCallback } from 'react';

import { config } from './config';

// ── Domain types ──────────────────────────────────────────────────────────────

export type TaskStatus   = 'TODO' | 'IN_PROGRESS' | 'DONE';
export type TaskPriority = 'LOW' | 'MEDIUM' | 'HIGH';

export type Task = {
  id:          string;
  companyId:   string;
  title:       string;
  description: string | null;
  status:      TaskStatus;
  priority:    TaskPriority;
  assigneeId:  string | null;
  createdBy:   string;
  createdAt:   string;
  updatedAt:   string;
};

export type Me = {
  subject:   string;
  companyId: string;
  roles:     string[];
};

/** Spring Data Page response shape. */
export type Page<T> = {
  content:       T[];
  totalElements: number;
  totalPages:    number;
  number:        number;
  size:          number;
};

// ── API client ────────────────────────────────────────────────────────────────

export class ApiError extends Error {
  status: number;
  constructor(status: number, message: string) {
    super(message);
    this.status = status;
  }
}

/**
 * Wraps fetch with the Auth0 access token — provided, do not modify.
 *
 * The SDK handles token caching and refresh; callers never see or store the token.
 * The backend decides whether to trust it.
 */
export function useApi() {
  const { getAccessTokenSilently } = useAuth0();

  return useCallback(
    async <T,>(path: string, init: RequestInit = {}): Promise<T | null> => {
      const token = await getAccessTokenSilently();

      const response = await fetch(`${config.apiBase}${path}`, {
        ...init,
        headers: {
          ...init.headers,
          Authorization: `Bearer ${token}`,
          ...(init.body ? { 'Content-Type': 'application/json' } : {}),
        },
      });

      if (!response.ok) {
        const message =
          response.status === 403 ? 'Forbidden — your role does not allow this action.' :
          response.status === 422 ? 'Invalid status transition.' :
          `Request failed (${response.status})`;
        throw new ApiError(response.status, message);
      }

      return response.status === 204 ? null : ((await response.json()) as T);
    },
    [getAccessTokenSilently],
  );
}

// ── Typed fetch helpers (TODO: implement these) ───────────────────────────────

// Hint: each function below should call the useApi hook result and hit the right endpoint.
// These are NOT React hooks — they receive the api function as an argument.

export async function fetchMe(
  api: ReturnType<typeof useApi>,
): Promise<Me> {
  // TODO: GET /api/me
  throw new Error('not implemented');
}

export async function fetchTasks(
  api: ReturnType<typeof useApi>,
  params?: { status?: TaskStatus; assigneeId?: string; page?: number; size?: number },
): Promise<Page<Task>> {
  // TODO: GET /api/tasks with query params
  throw new Error('not implemented');
}

export async function createTask(
  api: ReturnType<typeof useApi>,
  body: { title: string; description?: string; priority: TaskPriority; assigneeId?: string },
): Promise<Task> {
  // TODO: POST /api/tasks
  throw new Error('not implemented');
}

export async function updateTaskStatus(
  api: ReturnType<typeof useApi>,
  taskId: string,
  status: TaskStatus,
): Promise<Task> {
  // TODO: PATCH /api/tasks/{taskId}/status
  throw new Error('not implemented');
}

export async function assignTask(
  api: ReturnType<typeof useApi>,
  taskId: string,
  assigneeId: string,
): Promise<Task> {
  // TODO: PATCH /api/tasks/{taskId}/assignee
  throw new Error('not implemented');
}

export async function deleteTask(
  api: ReturnType<typeof useApi>,
  taskId: string,
): Promise<void> {
  // TODO: DELETE /api/tasks/{taskId}
  throw new Error('not implemented');
}
