package css.web.service.auth.consts;

import org.apache.commons.lang3.StringUtils;

/**
 * 定义系统中的角色常量
 *
 * @author yangshuo
 *
 */
public final class RoleConsts {
	/** 学生 */
	public static final String STUDENT = "student";

	/** 教师 */
	public static final String TEACHER = "teacher";

	/** 管理员 */
	public static final String ADMIN = "admin";

	public static final boolean isStudent(final String role) {
		return StringUtils.equals(STUDENT, role);
	}

	public static final boolean isTeacher(final String role) {
		return StringUtils.equals(TEACHER, role);
	}

	public static final boolean isAdmin(final String role) {
		return StringUtils.equals(ADMIN, role);
	}
}
